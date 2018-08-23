package project.dblearning.objects;

public class CardGlossary {
    private String name;
    private String description;

    public CardGlossary(){

    }

    public CardGlossary(long id, String name, int colorResource, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}