package com.phenix.adobepremiereproject.setting;

import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public class CompileSettings {

    /**
     * 
     */
    private final int ObjectID;

    /**
     * 
     */
    private final int VideoCompileSettings_ObjectRef;

    /**
     * 
     */
    private final int AudioCompileSettings_ObjectRef;

    /**
     * 
     * @param ObjectID
     * @param VideoCompileSettings_ObjectRef
     * @param AudioCompileSettings_ObjectRef 
     */
    public CompileSettings(int ObjectID, int VideoCompileSettings_ObjectRef, int AudioCompileSettings_ObjectRef) {
        this.ObjectID = ObjectID;
        this.VideoCompileSettings_ObjectRef = VideoCompileSettings_ObjectRef;
        this.AudioCompileSettings_ObjectRef = AudioCompileSettings_ObjectRef;
    }

    /**
     * 
     * @param file 
     */
    public void toXML(PrintWriter file) {
        file.append("\t<CompileSettings ObjectID=\"" + ObjectID + "\" ClassID=\"18a35d66-597e-4157-b783-938b5bec3547\" Version=\"4\">\n");
        file.append("\t\t<VideoCompileSettings ObjectRef=\"" + this.VideoCompileSettings_ObjectRef + "\"/>\n");
        file.append("\t\t<AudioCompileSettings ObjectRef=\"" + this.AudioCompileSettings_ObjectRef + "\"/>\n");
        file.append("\t\t<CompilerClassIDFourCC>0</CompilerClassIDFourCC>\n");
        file.append("\t\t<CompilerFourCC>0</CompilerFourCC>\n");
        file.append("\t\t<ExportVideo>true</ExportVideo>\n");
        file.append("\t\t<ExportAudio>true</ExportAudio>\n");
        file.append("\t\t<AddToProjectWhenFinished>true</AddToProjectWhenFinished>\n");
        file.append("\t\t<BeepWhenFinished>false</BeepWhenFinished>\n");
        file.append("\t\t<ExportWorkAreaOnly>false</ExportWorkAreaOnly>\n");
        file.append("\t\t<EmbedProjectLink>false</EmbedProjectLink>\n");
        file.append("\t</CompileSettings>\n");
    }
}
