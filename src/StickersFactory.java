import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersFactory {

    public void make(InputStream inputStream, String nameOfFile) throws Exception {

        // leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte
        var subtitle = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(subtitle);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 200, newHeight - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("output/" + nameOfFile));

    }

}
