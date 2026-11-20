/
public class ProsesAkhirBulan {

    public void potongBiayaAdmin(Rekening[] semua) {
        for (Rekening r : semua) {
            int biaya = r.potongBiayaAdmin();
            System.out.printf("%s %-13s: %s%n", r.getNomor(), "biaya admin", Rekening.formatRupiah(biaya));
        }
    }

    public void tambahBunga(BerBunga[] berbunga) {
        for (BerBunga b : berbunga) {
            int bunga = b.tambahBunga();
            System.out.printf("%s %-13s: %s%n", b.getNomor(), "bunga", Rekening.formatRupiah(bunga));
        }
    }

    // LSP: aman memanggil tarik() pada semua Rekening karena kontraknya
    // tidak pernah melempar exception (tidak perlu try-catch / instanceof)
    public void autodebet(Rekening[] semua, int jumlah) {
        for (Rekening r : semua) {
            boolean berhasil = r.tarik(jumlah);
            System.out.printf("%s %-13s: %s%n", r.getNomor(), "autodebet",
                    berhasil ? Rekening.formatRupiah(jumlah) : "gagal");
        }
    }
}