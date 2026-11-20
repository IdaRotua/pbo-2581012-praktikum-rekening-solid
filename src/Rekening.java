public abstract class Rekening {
    private String nomor;
    private String pemilik;
    protected int saldo;   //nduknya. Tabungan dan Giro mewarisinya

    public Rekening(String nomor, String pemilik, int saldo) {
    this.nomor = nomor;
    this.pemilik = pemilik;
        this.saldo = saldo;        //cetak info di hilangkan (SRP itu Single Responsibility Principle,
        // artinya satu class hanya boleh punya satu alasan untuk berubah
    }

    public String getNomor() { return nomor; }
    public String getPemilik() { return pemilik; }
    public int getSaldo() { return saldo; }

    public abstract int biayaAdminBulanan();

    public abstract String keterangan();     //OCP = Open/Closed Principle
                                         //Karena isinya diserahkan ke subclass, saya bisa menambah //
                                         // Deposito tanpa mengubah kode yang sudah ada.
    public int potongBiayaAdmin() {
        int biaya = biayaAdminBulanan();
        saldo -= biaya;
        return biaya;
    }

    public boolean setor(int jumlah) {
        if (jumlah <= 0) {
            return false;
        }
        saldo += jumlah;
        return true;
    }

    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || jumlah > saldo) {     //LSP (Liskov Substitution Principle)
            return false;                       //LSP artinya child bisa menggantikan parent tanpa merusak program. //
            // Tabungan memenhi karena memakai tarik() dari Rekening
                                      //
        }
        saldo -= jumlah;
        return true;
    }

    protected static String formatRupiah(int angka) {
        String digit = Integer.toString(Math.abs(angka));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit.length(); i++) {
            if (i > 0 && (digit.length() - i) % 3 == 0) {
                sb.append('.');
            }
            sb.append(digit.charAt(i));
        }
        return "Rp" + (angka < 0 ? "-" : "") + sb;
    }
}