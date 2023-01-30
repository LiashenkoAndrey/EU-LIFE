package eulife.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String university;

    @Nullable
    private String faculty;

    @Nullable
    private String github;

    @Nullable
    private String linkedin;

    @Nullable
    private String site;

    @Nullable
    private String age;


    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public UserDetails() {
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", university='" + university + '\'' +
                ", faculty='" + faculty + '\'' +
                ", github='" + github + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", site='" + site + '\'' +
                ", age='" + age + '\'' +
                ", image=" + image +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(university, that.university) && Objects.equals(faculty, that.faculty) && Objects.equals(github, that.github) && Objects.equals(linkedin, that.linkedin) && Objects.equals(site, that.site) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(university, faculty, github, linkedin, site, age);
    }
}
