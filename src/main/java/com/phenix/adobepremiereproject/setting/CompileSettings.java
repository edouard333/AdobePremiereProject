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
    private final int objectID;

    /**
     *
     */
    private final int videoCompileSettings_ObjectRef;

    /**
     *
     */
    private final int audioCompileSettings_ObjectRef;

    /**
     *
     * @param objectID
     * @param videoCompileSettings_ObjectRef
     * @param audioCompileSettings_ObjectRef
     */
    public CompileSettings(int objectID, int videoCompileSettings_ObjectRef, int audioCompileSettings_ObjectRef) {
        this.objectID = objectID;
        this.videoCompileSettings_ObjectRef = videoCompileSettings_ObjectRef;
        this.audioCompileSettings_ObjectRef = audioCompileSettings_ObjectRef;
    }

    @Override
    public String toXML() {
        StringBuilder stringBuilder= new StringBuilder();
        
        stringBuilder.append("\t<CompileSettings ObjectID=\"" + objectID + "\" ClassID=\"18a35d66-597e-4157-b783-938b5bec3547\" Version=\"4\">\n");
        stringBuilder.append("\t\t<VideoCompileSettings ObjectRef=\"" + this.videoCompileSettings_ObjectRef + "\"/>\n");
        stringBuilder.append("\t\t<AudioCompileSettings ObjectRef=\"" + this.audioCompileSettings_ObjectRef + "\"/>\n");
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
