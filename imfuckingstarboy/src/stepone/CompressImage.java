package stepone;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class CompressImage {

	public static void qqweqwe() {
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public static void qqq(final String filePath) throws IOException {
		final File file = new File(filePath);
		for (final File f : file.listFiles()) {
			if (f.getName().indexOf(".") < 0) {
				continue;
			}

			if (f.length() < 50000) {
				continue;
			}
			final BufferedImage bi = ImageIO.read(f);
			final File f2 = new File(
					"C:\\Users\\libo\\Desktop\\junk\\111\\2.jpg");
			final FileOutputStream fo = new FileOutputStream(f2);
			final ImageWriter writer = ImageIO.getImageWritersBySuffix("jpg")
					.next();
			final ImageOutputStream out = ImageIO.createImageOutputStream(fo);
			writer.setOutput(out);
			int i = 1;
			while (f.length() / i > 50000) {
				i++;
			}
			final ImageWriteParam param = writer.getDefaultWriteParam();
			if (param.canWriteCompressed()) {
				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				param.setCompressionQuality(1f / i);
				writer.write(null, new IIOImage(bi, null, null), param);
			}
			fo.close();
			out.close();
			writer.dispose();

			copyFile(f2, f);
			f2.delete();

		} ;

		// String fileName = file.getName();
		// System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
		// System.out.println(file.length());
		// try (FileInputStream fis = new FileInputStream(filePath)) {
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
	}

	public static void copyFile(final File fromFile, final File toFile)
			throws IOException {
		final FileInputStream ins = new FileInputStream(fromFile);
		final FileOutputStream out = new FileOutputStream(toFile);
		final byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, n);
		}

		ins.close();
		out.close();
	}

	public static void www(final String filePath) throws IOException {
		final File file = new File(filePath);
		for (final File f : file.listFiles()) {
			if (f.getName().indexOf(".") < 0) {
				continue;
			}
			int i = 1;
			while (f.length() / i > 50000) {
				i++;
			}
			// Thumbnails.of(f).scale(1f)
			// .outputQuality(
			// Double.valueOf(Double.valueOf(1).doubleValue()
			// / Double.valueOf(i).doubleValue()))
			// .toFile(filePath + "\\222\\" + f.getName());
		}

	}

	public static void asd(final String path) throws IOException {
		// Thumbnails.of(path).scale(1d).outputQuality(1f /
		// 7).outputFormat("jpg")
		// .toFile("C:\\Users\\libo\\Desktop\\junk\\111\\222\\3");
	}

	public static void main(final String[] args) throws IOException {
		// www("C:\\Users\\libo\\Desktop\\junk\\111");
		qqq("C:\\Users\\libo\\Desktop\\junk\\111");
		// System.out.println("111.jgp".substring("111.jgp".lastIndexOf(".")));
		// asd("C:\\Users\\libo\\Desktop\\junk\\111\\3.png");
	}

}
