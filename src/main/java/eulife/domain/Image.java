package eulife.domain;

import jakarta.persistence.*;

@Entity
public class Image {

    public Image() {
    }

    public Image(String filename, byte[] binary_image) {
        this.filename = filename;
        this.binary_image = binary_image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String filename;

    @Lob
    private byte[] binary_image;

    public Long getId() {
        return id;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



    public byte[] getBinary_image() {
        return binary_image;
    }

    public void setBinary_image(byte[] binary_image) {
        this.binary_image = binary_image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
