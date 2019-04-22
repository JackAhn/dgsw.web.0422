package kr.hs.dgsw.demo.Protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentProtocol {

    private String storagePath;
    private String originalName;

    public AttachmentProtocol(String storagePath, String originalName){
        this.storagePath = storagePath;
        this.originalName = originalName;
    }
}
