public class Tabungan extends Rekening implements BerBunga {
    private double bungaPersen;   //OCP (Open/Closed Principle) //terbuka untuk ekstensi tapi tertup untuk medif
          //Tabungan adalah hasil ekstensi
    //bukan dengan mengedit Rekening

    public Tabungan(String nomor, String pemilik, int saldo, double bungaPersen) {
        super(nomor, pemilik, saldo);
        this.bungaPersen = bungaPersen;
    }

    @Override
    public int tambahBunga() {
        int bunga = (int) (saldo * bungaPersen / 100);
        setor(bunga);
        return bunga;
    }

    // gratis kalau saldo minimal 1.000.000, selain itu Rp5.000
    @Override
    public int biayaAdminBulanan() {
        return saldo >= 1000000 ? 0 : 5000;
    }

    @Override
    public String keterangan() {
        return "Tabungan, bunga " + bungaPersen + "% per bulan";
    }
}