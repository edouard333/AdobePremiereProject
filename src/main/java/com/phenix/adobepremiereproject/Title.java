package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.AdobeTitle.AdobeTitle;
import com.phenix.adobepremiereproject.AdobeTitle.Text;
import java.io.PrintWriter;

/**
 * Titrage dans Adobe Premiere.
 *
 * @author Edouard Jeanjean <edouard128@hotmail.com>
 */
public class Title extends ElementInSequence {

    private int id;

    /**
     * Les données du titre.
     */
    private AdobeTitle adobeTitle;

    /**
     * Nombre de séquence qu'il y a dans le projet.
     */
    private static int nombre_title = 1;

    /**
     *
     * @param name
     */
    public Title(String name) {
        this(null, name);
    }

    /**
     *
     * @param parent
     * @param name
     */
    public Title(Folder parent, String name) {
        super(parent, name, Sequence.TITLE);

        adobeTitle = new AdobeTitle();

        nombre_title++;
    }

    /**
     *
     * @return
     */
    public static int getTitleNumber() {
        return nombre_title;
    }

    @Override
    public void toXML(PrintWriter file, int order) {
        file.append("    <ClipProjectItem ObjectUID=\"" + this.current_ObjectURef + "\" ClassID=\"" + this.classID + "\" Version=\"1\">\n");
        file.append("		<ProjectItem Version=\"1\">\n");
        file.append("			<Node Version=\"1\">\n");
        file.append("				<Properties Version=\"1\">\n");
        file.append("					<Column.PropertyText.Label>BE.Prefs.LabelColors.3</Column.PropertyText.Label>\n");
        file.append("					<project.icon.view.grid.order>" + order + "</project.icon.view.grid.order>\n");
        file.append("				</Properties>\n");
        file.append("			</Node>\n");
        file.append("			<Name>TITRE</Name>\n");
        file.append("		</ProjectItem>\n");
        file.append("		<MasterClip ObjectURef=\"8c85bb49-dcaf-4511-aed8-9f6cead61d2a\"/>\n");
        file.append("	</ClipProjectItem>\n");
    }

    /**
     * XML qu'il y a dans une séquence.
     *
     * @param file
     */
    public void clipLoggingInfo(PrintWriter file) {
        file.append("\t<ClipLoggingInfo ObjectID=\"45\" ClassID=\"77ab7fdd-dcdf-465d-9906-7a330ca1e738\" Version=\"7\">\n");
        file.append("\t	<CaptureMode>2</CaptureMode>\n");
        file.append("\t	<ClipName>TITRE</ClipName>\n");
        file.append("\t	<MediaFrameRate>10584000000</MediaFrameRate>\n");
        file.append("\t	<TimecodeFormat>100</TimecodeFormat>\n");
        file.append("\t</ClipLoggingInfo>\n");
        file.append("\t<VideoClip ObjectID=\"46\" ClassID=\"9308dbef-2440-4acb-9ab2-953b9a4e82ec\" Version=\"11\">\n");
        file.append("\t	<Clip Version=\"18\">\n");
        file.append("\t		<Node Version=\"1\">\n");
        file.append("\t			<Properties Version=\"1\">\n");
        file.append("\t				<BE.Prefs.SyntheticMedia.DefaultIsDropFrame>false</BE.Prefs.SyntheticMedia.DefaultIsDropFrame>\n");
        file.append("\t				<asl.clip.label.color>14910691</asl.clip.label.color>\n");
        file.append("\t				<asl.clip.label.name>BE.Prefs.LabelColors.3</asl.clip.label.name>\n");
        file.append("\t			</Properties>\n");
        file.append("\t		</Node>\n");
        file.append("\t		<Source ObjectRef=\"83\"/>\n");
        file.append("\t		<ClipID>d55680c2-20d1-4bb5-b02a-c27be5413715</ClipID>\n");
        file.append("\t		<InPoint>0</InPoint>\n");
        file.append("\t		<OutPoint>1270080000000</OutPoint>\n");
        file.append("\t		<InUse>false</InUse>\n");
        file.append("\t	</Clip>\n");
        file.append("\t</VideoClip>\n");
        file.append("\t<ClipChannelGroupVectorSerializer ObjectID=\"47\" ClassID=\"a3127a8c-95d4-456e-a7f5-171b3f922426\" Version=\"1\">\n");
        file.append("\t</ClipChannelGroupVectorSerializer>\n");
    }

    //@Override
    public void videoMediaSource(PrintWriter file) {
        file.append("\t<VideoMediaSource ObjectID=\"83\" ClassID=\"e64ddf74-8fac-4682-8aa8-0e0ca2248949\" Version=\"2\">\n");
        file.append("\t\t<MediaSource Version=\"3\">\n");
        file.append("\t\t\t<Content Version=\"10\">\n");
        file.append("\t\t\t</Content>\n");
        file.append("\t\t\t<Media ObjectURef=\"285b90b1-ba3a-48f2-9807-34d474e53f16\"/>\n");
        file.append("\t\t</MediaSource>\n");
        file.append("\t\t<OriginalDuration>10973491200000000</OriginalDuration>\n");
        file.append("\t</VideoMediaSource>\n");
    }

    @Override
    public void media(PrintWriter file) {
        file.append("\t<Media ObjectUID=\"285b90b1-ba3a-48f2-9807-34d474e53f16\" ClassID=\"7a5c103e-f3ac-4391-b6b4-7cc3d2f9a7ff\" Version=\"26\">\n");
        file.append("\t	<VideoStream ObjectRef=\"102\"/>\n");
        file.append("\t	<ImporterPrefs Encoding=\"base64\" BinaryHash=\"08750ff2-29d4-c8a2-ee5f-bb8400000e04\">AQAAAAAAAABgoAAAAAAAAENvbXByZXNzZWRUaXRsZQB4nO0da1MbOVI/xXXfgx9gHKrm2CIEbqkjIYXZJHxKGTDEe47ts00C9+Pvrh/S6D1jHmHGGxUFzHS3pJbU3Wp1z2j+999M/CbuxDcxFg3xXQzFXCzESEzFRPxd/E20xYZowf8GYCbiEuBXgJ2IG8L+Ic7EoXgFVNtE85vYFZnYA5qpuIASX8QpXE3F0oOfQS1LaHNImI9Wu7uiA222xGv47cFvByiaHk0m3lHNfP9F9AG/pHvkbkEUH6CFgbgH7CFQzcUPuJsDF7tANxe3UAJrjlNlUOuS7paAVe1cwv2QxmNXXAN2DK1xTWXUyNE8H5cx/OxC/5gHF56JA6hrQdAjKs2ULhTpJsAttngNP8UcFtMif1OgXQY59DEmjycwnssAlwqO0PIZaxZICfZ3QfyOCD8XxzRvU2phAfL3MSq/WN6kdu8PaDyGUPuS2rkGeKi+jqzLpD+DWmbEo+p7CMf8o/ZcerNiYpCvEYzHAErf0twsPPoQRSZ+p5HEet4SfkSj4paNUXG9xSPijhrW8I1kR41R0ZjNxGeQoiP4ewC19AHWFjuk69i2j8US53B9JN4DjOnZKjC9jUPqvtgH6TyAn/cgR6dUgml9TAZ9nwOPP6AfX6knc9lbZWEU/pTG6Sv1F+8nVGdZaXc8/bFyR3OPtIGlG+/VvPaBBmdCa8MetTYgLN5fS83bkP3VsAz+TqH/JlZBMmqLe8b4HcKb0Ax4mRJfU+DepLLhbP2K+FTYPWOU4v1oiW5hTxS+qC/dFXvTLelPjOPmSrP3Bmguxb+kvNyS7oW0ZEtqSR94HQAN4k+p90NqE1dftCNbwPUOaAFzWEybgfX114ATKXszgKItUBb4FO7xP5ac0Er4jcqZvWRtipdDfKjHiHlLHA4AMoayuFbe57Y93tNtqjPcu2ZhnTbuDO7uSA+H+Soys/Q1zkPvATyUt/MPgA0A9pUs/gnIy5/Sdtg1sfwMafz2qYReTRbi30A1IF6QhzBVBjVPaZYUbB9qnpIk4Jzo+c");
        file.append("E6imn9uk7o76S0Hk2HWo6YofgE/6/Idu7mmu5jMpDAEenSnNY8vlMlwrgMbDKvcG8A9x+4Zk1ztSBGlRm92SctmBDuEGjGJFs8M5sgFRvASeOZrthWrdJyBrIyJqma0dy8NJert47UD5N2pT37ZNlwhkJUpnVSa8cg35koP8yFsvW/Iy2ZEF+4frtyEaZBqZrSruCT7I+9h/CxGWkP2kEcpWMp3Qvwb9wW43SxOs5XrONc7rvGtAZOpP+BWqLWVfa4fXxGO5kL8thxfTEt6yfZV15/Y1QZ6eSM6r4k3Twj2j6tH5f5rLRpr4d1rUYfq1dbPbNvZbQZ7dbG3ngqqBoHJc32nSuHLDvv11w6zZKPk83yGpJk/lzJDEviRT4b9ZBRd0VOUvqrSWlMHhey1YlsZf3kM63xfw0ZdSWxuaKPWrwPDEcrVQyguGwjsBPl0rxfVnVw//v091Jq1Qj2OkPaF2zLGdEwHIc78Ttc64iJnjkfx/T/JD50fNqGoUZyJFiNYZ92AirSw/oYp1hd87HdvtwB4wz08giRDXcpQxGSEIUqNya7sbT6a0KZ7g/axeEoaNnx2whRcXlzF2zPgb0/5nJsE3x7YeIywI5gt4ux+6ucexvGNEekAaxPJp0NZ9prkvkx2O8BWZZdsDT7NJ9jktUPZF9fkcTeEC3GLFSNfmlV65RG85DguKe8t1pQGYKG1VZDtqZrL6rFbsm0ajFefUq7Dm1xtr1SpjW6phEcUOzihuzsvcCYBurka8p47cCcd/I6fFq2Qr5+N0ssx1NtSy/ZlmRbkm1JtiViW8poVD5L9dK9b4AcvA34MprijdTnIu9pmY9Hl6RbS7gepxlFtkc5Z28p74MyPRAqtq/yjOWUOq7N0U60HcrLDulNiC6j7Dr3km2wnQHxsRnVjRxgbhHxU/JpcVfxKtetGEWs9LmhlzE88sqZJ9w7fYG7KdlU3p");
        file.append("EsBGd2metiuoxmZE6YibQAS5JDNfph7MOs0wHM2AWtAmPqy9d8F9SXK9Qp6aRvSR9SEp+eUJkxzsgNaGei9ZOlF7UO6y6j5gyfK/kIPyTpucl5OZYzFNKKrtQKt4y9ZvvrNT8hgi3q9ciGZYLzfmOjBhOS0WrOT42442piMuL+gOzGUK5aV3luEyXH592u7eHlMa/mQt3nOIooUHts3AmNjctZjCqjXSXr1+9yjcSZc8vHqHjsMf68JAuubPA2zDda+9f5bPg0sbbNbPKRHMc7skId0OYtskQ9uu4W8Barxcw3I+4dXN+RpcC80s/7a2eszZYRE5KMp2nKVqmm6BF8KW0xI5110hWTr6o0pbc2mtICaX7K78M1wYeqlYa9Ms1/0QrUjj7Z0nDWwPcgDd/ks33mk2MLr6TWQx/D62YI/nAONivnYKtyDrqVc7BdOQe9yjl4XTkHO5Vz0M6f5a2Sh3YNeKjeMrZrYBvbNbCO7UL72KG4yxaMVRf+dqVf/rN5elUooy5POy+mu61a6C9yUQcdbtG7LfXgow66jHzUQZ9b9HR5Pfio3vNhPqr3f5iP6r0g5qMevlA9/KFWTXyiVk38olZNfKNWTfyjVomP9Bg+bLgZlTBj5sxxLLukbNqvnV3qiZRdStmllF1K2aWUXUrZpZRdel5NSdmluCak7FL1u4Tq9wfVR1qqj7FUH12pPq5Sj4hK9TzUIZJSvWWsRwSleutYHDnhHfzL5G+Kc0pbxpPkLxOBrYOupkxSyiSlTFLKJD2Fj3r4PfXwfVImKWWS6ptJMu8XXh98SFEW6VJygPjw2W5hiozGBuNrfPKj3fMwLqNzW8eUYTmx3tjaki2FsfYY85uKZkzNx2YUbxxZGQx+x5DjjD7OLMExOTsnEMPiO/2c0eBzSc8EnwOo3swMY91Sb4R55l8nWNam0TXsedmJECYj+cVzdceUY+F3Oc0MYByv29oHqbsMtmVi+FywAeW+MAtkSm9LznQMr9tyY68+PKMzv1hi1BuCDZqlK5I/lHO1mqAcXhl1qZzZDV3zqXBqpjWET6q5tUZJ3WfQAsYZ5zlO3+Odz9tqHLfXjuPO2nG8uXYcbzkcdyjrH+da42Oca4qncq9to2+LwjjTGmkb7Noh0zpraOi");
        file.append("MW7dk+Bxcjf8gRtKGuCfy+BRmOb9/YZzdP1ydZnIFcnuocW4ZHDX7PIA43izLZzJ/DeTXwjRm2Xe0hvIJquYKG6bgdZllK3TCgIt1fZzn8lN2HuGnmM+c1NNbSb7Kr+6rdCh3W2zju6U2Xp0qnfyW5LfUw2/ZLJHpzVKZ3kx+S/Jb1tpv0Vmql4ivqJaSx5I8lvWLrmAsvpc8lbXieP09lao5Tt5J8k6q8U7C74o/Jr6y/ShvJcVXkreS4ivJa0leS4qvJA8meTBP92AekyFKkZbku9TXd0mRluSz/FV8lqo5Tn5K8lMe66fYEPuLS+Y30W1YyKNR7yM8Pp5iw9VXIBReva3k1+BSsneDa5Q6E8Y8/0NHZ2IUGc3rd+rhknTOpvTPtSimxnfbb8ljYA7HUjPcemJUZnn17rsrIWEKlJBLamMoPhs9bEv5COHsMiFam+a8oN7zSL3nQVqWgVHeitk/E66p3K8PmvBM8Ek0+K02v+9hnF2mD/ALA9vySroUrgx/pu93jGlGwh65TWG2749rGGeXKePZp3B5Pi/l+dzh2cbqE0P8kvokA+RoRpann0uA2U8f57azl1N9BynCrxndR1oNU5qeewjv+vDhOmwP+kyuta5mx6jcPoW/c+RT4NkTOJK87i2kL7DM+x/DIs625M9n24ufRUy2Pdn2ZNuTbU+2/WVtuwtjb/4YLMkN2TGmse+Pgcd7ofcD+h7v3tK4/aB3DG/yVkJQ1XIIpr/5yntudcdfh5tJi3sfXI3Um76P+c7wh9x6630pSi2eBNUWG+K16MjsAcsySkJH4KkAiNuUEUH1fbdQbeau+Y5Kd6DkBr0J23Vq1k/RqRrN0qgbuDOzd6EutB7f9n7K96J/la8nNwvk3NYCLqt23nhtv08bgsVKs76p+QrpE8YMGwK/1vintCbqfNc2YWZ5iYXg8+hsCmxHnXtp92tEElJ03iV+LVOfmdkBLdwGXdmEK2Xt76wzNT9LnQtprMaVn3n5PeeQVyE1Tq7Mxukya8SOg7paRIH4olFDigOK/Y+llcFyJ0aNWrb099+x1lVLPfZr56dQx7E3UgqKMndK+jnP+3pMEolrFkuvi8/IZ5nQGCEEv5zKvWBtv5LSPSf95Lfa98RS1nFB/rEas9UoG7JF9OlvqWfqBLOGYVvYW7dPOm7kMuBityX2c/5tXe6fyn8wjmV0GaVRMuF/adelbEoper6xW9JKbX5/dSKvdWs+TdOzMjbUXuvr6gHg6tADS7JBZyO46/QOreG93NY8zAPoQskNsm7JA0gewDp5AJ1SD6Dz7B5Ah3SlJZ96iHsAvsYmD6CuHsBHudfHXWi9fYBeiQ+wXegD9JIPsIIPoOFmbEDnNnltuAUpZZ70fYNy6Xzt7kFmNAaX0NIZ/OKq5Z8+7VNkpHcDabN1nMmFZpYOYdsNsA1YO66OerekxkjzXN6DTk160CnoQbNgfppOjKjpxZA+AqRPV4diZOiV+Sxb0anU17KUazOaUQyO2H4em8OVhWPSNlTRsZ7YmQgbg5A474zXvVQjMCXdYsgR0GJMnHWOT8nSNGok98QVrRpD8YUi/lMq/3/c1EFA\n");
        file.append("\t	</ImporterPrefs>\n");
        file.append("\t	<FilePath>1414091852</FilePath>\n");
        file.append("\t	<Infinite>true</Infinite>\n");
        file.append("\t	<ImplementationID>ad0be7ce-e015-4877-9777-93c98f30dcac</ImplementationID>\n");
        file.append("\t	<Title>TITRE</Title>\n");
        file.append("\t	<ActualMediaFilePath>1414091852</ActualMediaFilePath>\n");
        file.append("\t	<ContentAndMetadataState>00000000-0000-0000-0000-000000000000</ContentAndMetadataState>\n");
        file.append("\t	<ConformedAudioRate>9223372036854775807</ConformedAudioRate>\n");
        file.append("\t</Media>\n");
    }

    /**
     * Ajout d'un text.
     *
     * @param text
     */
    public void addText(Text text) {
        adobeTitle.texts.add(text);
    }

    @Override
    void inSequence(PrintWriter file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
