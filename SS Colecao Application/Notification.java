public class Notification{
    private String titleString;
    private String notiString;

    public Notification(String titleString, String notiString){
        this.titleString=titleString;
        this.notiString=notiString;
    }

    public String getTitle(){
        return titleString;
    }

    public String getNotiText(){
        return notiString;
    }

    public String toString(){
        return getTitle()+"\t"+getNotiText();
    }
}
