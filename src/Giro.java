// OCP: Giro adalah hasil ekstensi dari Rekening, jenis baru tanpa mengedit Rekening.
// LSP: Giro bisa menggantikan Rekening karena tarik() mematuhi kontrak
// (true kalau berhasil, false kalau gagal, tidak pernah melempar exception).
public class Giro extends Rekening {
    private int batasOverdraft;   // SRP: data khusus giro disimpan di sini

    public Giro(String nomor, String pemilik, int saldo, int batasOverdraft) {
        super(nomor, pemilik, saldo);
        this.batasOverdraft = batasOverdraft;
    }

    // LSP: aturannya beda dari Rekening (boleh sampai batas overdraft),
    // tapi tetap menepati kontrak: true/false, tanpa exception
    @Override
    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || saldo - jumlah < -batasOverdraft) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    // giro selalu Rp15.000
    // OCP: jawaban Giro atas method abstract Rekening
    @Override
    public int biayaAdminBulanan() {
        return 15000;
    }

    // SRP: hanya mengembalikan teks, tidak mencetak (pengganti cetakInfo)
    @Override
    public String keterangan() {
        return "Giro, batas overdraft " + formatRupiah(batasOverdraft);
    }
}