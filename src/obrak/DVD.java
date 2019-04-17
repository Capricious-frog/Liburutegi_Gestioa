package obrak;

public class DVD extends Multimedia {
    private String zuzendaria;

    public DVD(int eZenb, String sig, String izenb, String ekoizlea) {
        super(eZenb, sig, izenb, ekoizlea);
    }

    public String getZuzendaria() {
        return zuzendaria;
    }

    public void setZuzendaria(String zuzendaria) {
        this.zuzendaria = zuzendaria;
    }
}
