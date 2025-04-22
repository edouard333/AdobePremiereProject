package com.phenix.adobepremiereproject.setting;

import jakarta.validation.constraints.NotNull;
import java.io.PrintWriter;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class CompileSettings {

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
     * @param writer
     */
    public void toXML(@NotNull PrintWriter writer) {
        writer.append("\t<CompileSettings ObjectID=\"" + ObjectID + "\" ClassID=\"18a35d66-597e-4157-b783-938b5bec3547\" Version=\"4\">\n");
        writer.append("\t\t<VideoCompileSettings ObjectRef=\"" + this.VideoCompileSettings_ObjectRef + "\"/>\n");
        writer.append("\t\t<AudioCompileSettings ObjectRef=\"" + this.AudioCompileSettings_ObjectRef + "\"/>\n");
        writer.append("\t\t<CompilerClassIDFourCC>0</CompilerClassIDFourCC>\n");
        writer.append("\t\t<CompilerFourCC>0</CompilerFourCC>\n");
        writer.append("\t\t<ExportVideo>true</ExportVideo>\n");
        writer.append("\t\t<ExportAudio>true</ExportAudio>\n");
        writer.append("\t\t<AddToProjectWhenFinished>true</AddToProjectWhenFinished>\n");
        writer.append("\t\t<BeepWhenFinished>false</BeepWhenFinished>\n");
        writer.append("\t\t<ExportWorkAreaOnly>false</ExportWorkAreaOnly>\n");
        writer.append("\t\t<EmbedProjectLink>false</EmbedProjectLink>\n");
        writer.append("\t</CompileSettings>\n");
    }
}
