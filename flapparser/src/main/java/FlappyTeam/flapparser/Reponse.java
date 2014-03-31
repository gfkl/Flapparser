package FlappyTeam.flapparser;

/***/
public class Reponse {
    /***/private boolean value;
    /***/private String libele;

    /**
     * @param valueNew ,true/false
     * @param libeleNew , chaine de la reponse
     */
    public Reponse(final boolean valueNew, final String libeleNew) {
        super();
        this.value = valueNew;
        this.libele = libeleNew;
    }

    /**@return la valeur*/
    public final boolean isValue() {
        return value;
    }

    /**@param valueNew update value*/
    public final void setValue(final boolean valueNew) {
        this.value = valueNew;
    }

    /**@return le libele*/
    public final String getLibele() {
        return libele;
    }

    /**@param libeleNew update value libele*/
    public final void setLibele(final String libeleNew) {
        this.libele = libeleNew;
    }

    /**@param o ,object to compare
     * @return si les object sont egaux*/
    public boolean    equals(final Object o) {
        if (o instanceof Reponse) {
            if (((Reponse) o).getLibele().equals(this.libele)
                    && ((Reponse) o).isValue() == this.isValue()) {
                return true;
            }
        }
        return false;
    }
}
