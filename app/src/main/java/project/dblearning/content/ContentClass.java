package project.dblearning.content;

public class ContentClass {
    private String title;
    private String content;
    private String urlImg;

    public ContentClass() {
    }


    public ContentClass(String title, String content, String urlImg) {
        this.title = title;
        this.content = content;
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
