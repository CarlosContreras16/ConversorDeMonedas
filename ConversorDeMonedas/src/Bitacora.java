import javax.xml.stream.events.StartDocument;

public class Bitacora implements Comparable<Bitacora>{
    Double Monto;
    Double Total;
    Double dolar;

    private String Divisa;
    public String getDivisa() {
        return Divisa;
    }
    public void setDivisa(String divisa) {
        Divisa = divisa;
    }
    private Double getMonto() {
        return Monto;
    }
    private void setMonto(Double monto) {
        Monto = monto;
    }
    private Double getTotal() {
        return Total;
    }
    private void setTotal(Double total) {
        Total = total;
    }
    private Double getDolar() {
        return dolar;
    }
    private void setDolar(Double dolar) {
        this.dolar = dolar;
    }

    public Bitacora(MisConversiones misConversiones){
//        this.Monto = misConversiones.Monto();
//        this.Total = misConversiones.Total();
        this.Divisa = misConversiones.Divisa();
//        this.dolar = misConversiones.Dolar();
    }

    @Override
    public int compareTo(Bitacora otrabitacora){
        return this.getDivisa().compareTo(otrabitacora.getDivisa());
    }
    @Override
    public String toString() {
        return "(Divisa=" + Divisa + ")";
    }
//    @Override
//    public String toString(){
//        return  "(Conversiones= "+ + ")";
//    }
}
