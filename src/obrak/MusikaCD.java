package obrak;

public class MusikaCD extends Multimedia {
    private String interpretea;

    public MusikaCD(int eZenb, String sig, String izenb, String ekoizlea) {
        super(eZenb, sig, izenb, ekoizlea);
    }

    public String getInterpretea() {
        return interpretea;
    }

    public void setInterpretea(String interpretea) {
        this.interpretea = interpretea;
    }
}
