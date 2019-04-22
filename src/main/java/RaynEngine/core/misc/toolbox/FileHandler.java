package RaynEngine.core.misc.toolbox;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Vtboy on 1/10/2016.
 */
public class FileHandler {
    private boolean isRelease;
    private final String devPath = "src/main/resources";
    private final String releasePath = new File(FileHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();

    public FileHandler(boolean isRelease) {
        this.isRelease = isRelease;
    }

    public String getTexture(String fileName) {
        if (!isRelease)
            return devPath + "/" + fileName + ".png";
        else
            return releasePath + File.separator + "images" + File.separator + fileName + ".png";
    }

    public String getShader(String shaderName) {
        if (!isRelease)
            return devPath + "/" + shaderName + ".shader";
        else
            return releasePath + File.separator + "RaynEngine/core/render/shaders" + File.separator + shaderName + ".shader";
    }

    public String getFont(String name) {
        if (!isRelease)
            return devPath + "/" + name + ".fnt";
        else
            return releasePath + File.separator + "fonts" + File.separator + name + ".fnt";
    }

    public String getModel(String name) {
        if (!isRelease)
            return devPath + "/" + name + ".obj";
        else
            return releasePath + File.separator + "RaynEngine/core/models" + File.separator + name + ".obj";
    }

    public void loadNatives() throws URISyntaxException {
        if (!isRelease) {
            System.setProperty("org.lwjgl.librarypath", new File("target/natives").getAbsolutePath());
        } else {
            System.setProperty("org.lwjgl.librarypath", new File(releasePath + File.separator + "natives").getAbsolutePath());
        }
    }

    public void loadFromJar() {
        saveNatives();
        saveImages();
        saveModels();
        saveFonts();
        saveShaders();
    }

    private void saveNatives() {
        System.out.println("Starting to copy over natives...");
        try {
            File Natives = new File("natives");
            if (!Natives.exists())
                Natives.mkdir();
            for (String file : getFilesByExtension("dll"))
                ExportResource(file, Natives + File.separator + file);
            for (String file : getFilesByExtension("dylib"))
                ExportResource(file, Natives + File.separator + file);
            for (String file : getFilesByExtension("so"))
                ExportResource(file, Natives + File.separator + file);
            for (String file : getFilesByExtension("jnilib"))
                ExportResource(file, Natives + File.separator + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished copying natives...");
    }

    private void saveImages() {
        System.out.println("Starting to copy over images...");
        try {
            File Images = new File("images");
            if (!Images.exists())
                Images.mkdir();
            for (String file : getFilesByExtension("png")) {
                ExportResource(file, Images + File.separator + file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished copying images...");
    }

    private void saveModels() {
        System.out.println("Starting to copy over RaynEngine.core.models...");
        try {
            File Models = new File("RaynEngine/core/models");
            if (!Models.exists())
                Models.mkdir();
            for (String file : getFilesByExtension("obj"))
                ExportResource(file, Models + File.separator + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished copying RaynEngine.core.models...");
    }

    private void saveFonts() {
        System.out.println("Starting to copy over fonts...");
        try {
            File Fonts = new File("fonts");
            if (!Fonts.exists())
                Fonts.mkdir();
            for (String file : getFilesByExtension("fnt"))
                ExportResource(file, Fonts + File.separator + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished copying fonts...");
    }

    private void saveShaders() {
        System.out.println("Starting to copy over RaynEngine.core.render.shaders...");
        try {
            File Shaders = new File("RaynEngine/core/render/shaders");
            if (!Shaders.exists())
                Shaders.mkdir();
            for (String file : getFilesByExtension("shader"))
                ExportResource(file, Shaders + File.separator + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished copying RaynEngine.core.render.shaders...");
    }

    private void ExportResource(String in, String out) throws Exception {
        InputStream ddlStream = FileHandler.class.getClassLoader().getResourceAsStream(in);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(out);
            byte[] buf = new byte[2048];
            int r = ddlStream.read(buf);
            while (r != -1) {
                fos.write(buf, 0, r);
                r = ddlStream.read(buf);
            }
        } finally {
            if (fos != null) {
                fos.close();
                System.out.println("Copied " + out);
            }
        }
    }

    private List<String> getFilesByExtension(String extension) throws IOException {
        List<String> files = new ArrayList<String>();
        CodeSource src = FileHandler.class.getProtectionDomain().getCodeSource();
        if (src != null) {
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while (true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null)
                    break;
                String name = e.getName();
                if (name.contains("." + extension) && !name.contains("newdawn/slick"))
                    files.add(name);
            }
        }
        return files;
    }

    public String getPath() {
        if (isRelease) return releasePath;
        else return devPath;
    }

    public boolean isRelease() {
        return isRelease;
    }
}

