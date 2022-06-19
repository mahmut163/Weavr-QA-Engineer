package interviewfrontendtest.pages;

public enum FilterContent {
    A_TO_Z ("az"),
    Z_TO_A ("za"),
    LOW_TO_HIGH ("lohi"),
    HIGH_TO_LOW ("hilo");

    private String value;

    private FilterContent(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}


