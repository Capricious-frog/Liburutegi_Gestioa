package obrak;

public class MusikaCD extends Multimedia {
    private String interpretea;

    public MusikaCD(int eZenb, String sig, String izenb, String ekoizlea, String interpretea) {
        super(eZenb, sig, izenb, ekoizlea);
        this. interpretea = interpretea;
    }

    public MusikaCD(String[] alea) {
        super(Integer.parseInt(alea[1]), alea[2], alea[3], alea[4]);
        this.interpretea = alea[5];
    }

    public String getInterpretea() {
        return interpretea;
    }

    public void setInterpretea(String interpretea) {
        this.interpretea = interpretea;
    }
}
