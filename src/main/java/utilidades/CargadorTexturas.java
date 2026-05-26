package utilidades;

import org.lwjgl.BufferUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import static org.lwjgl.opengl.GL11.*;

public class CargadorTexturas {

    /**
     * Carga una textura desde la carpeta de recursos de Maven y la sube a OpenGL.
     * 
     * @param rutaResource Ruta relativa dentro de resources (ej. "/texturas/PAREDBLANCA.jpg")
     * @return El ID de la textura en OpenGL, o 0 si ocurrió un error.
     */
    public static int cargarTextura(String rutaResource) {
        try (InputStream in = CargadorTexturas.class.getResourceAsStream(rutaResource)) {
            if (in == null) {
                System.err.println("No se pudo encontrar el archivo de textura en la ruta: " + rutaResource);
                return 0;
            }
            BufferedImage image = ImageIO.read(in);
            
            int width = image.getWidth();
            int height = image.getHeight();
            
            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
            
            // Cada píxel consta de 4 bytes (Red, Green, Blue, Alpha)
            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
            
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];
                    // Pixel está en formato ARGB, OpenGL prefiere RGBA
                    buffer.put((byte) ((pixel >> 16) & 0xFF)); // Rojo
                    buffer.put((byte) ((pixel >> 8) & 0xFF));  // Verde
                    buffer.put((byte) (pixel & 0xFF));         // Azul
                    buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
                }
            }
            buffer.flip();
            
            // Generar y configurar el ID de textura
            int textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);
            
            // Configurar parámetros de repetición y filtrado
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            
            // Subir los bytes a OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            
            // Desenlazar la textura para evitar efectos secundarios
            glBindTexture(GL_TEXTURE_2D, 0);
            
            System.out.println("Textura cargada exitosamente: " + rutaResource + " (ID: " + textureID + ", " + width + "x" + height + ")");
            return textureID;
        } catch (IOException e) {
            System.err.println("Error al cargar la textura: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
