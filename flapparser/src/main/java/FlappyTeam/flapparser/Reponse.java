package FlappyTeam.flapparser;

public class Reponse {
    private boolean value;
    private String libele;

    public Reponse(boolean value, String libele) {
        super();
        this.value = value;
        this.libele = libele;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }
}
