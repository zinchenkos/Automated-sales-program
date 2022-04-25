package sample.pojo;

public class Manufactory {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Manufactory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
