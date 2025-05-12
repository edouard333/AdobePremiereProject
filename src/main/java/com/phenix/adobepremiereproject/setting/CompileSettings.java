package com.phenix.adobepremiereproject.setting;

import com.phenix.adobepremiereproject.internal.XMLSimpleConvertible;

/**
 *
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
public final class CompileSettings implements XMLSimpleConvertible {

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

    @Override
    public String toXML() {
        StringBuilder stringBuilder= new StringBuilder();
        
        stringBuilder.append("\t<CompileSettings ObjectID=\"" + ObjectID + "\" ClassID=\"18a35d66-597e-4157-b783-938b5bec3547\" Version=\"4\">\n");
        stringBuilder.append("\t\t<VideoCompileSettings ObjectRef=\"" + this.VideoCompileSettings_ObjectRef + "\"/>\n");
        stringBuilder.append("\t\t<AudioCompileSettings ObjectRef=\"" + this.AudioCompileSettings_ObjectRef + "\"/>\n");
        stringBuilder.append("\t\t<CompilerClassIDFourCC>0</CompilerClassIDFourCC>\n");
        stringBuilder.append("\t\t<CompilerFourCC>0</CompilerFourCC>\n");
        stringBuilder.append("\t\t<ExportVideo>true</ExportVideo>\n");
        stringBuilder.append("\t\t<ExportAudio>true</ExportAudio>\n");
        stringBuilder.append("\t\t<AddToProjectWhenFinished>true</AddToProjectWhenFinished>\n");
        stringBuilder.append("\t\t<BeepWhenFinished>false</BeepWhenFinished>\n");
        stringBuilder.append("\t\t<ExportWorkAreaOnly>false</ExportWorkAreaOnly>\n");
        stringBuilder.append("\t\t<EmbedProjectLink>false</EmbedProjectLink>\n");
        stringBuilder.append("\t</CompileSettings>\n");
        
        return stringBuilder.toString();
    }
}
