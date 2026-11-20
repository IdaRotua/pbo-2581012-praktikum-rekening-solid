// SRP: class ini hanya mengurus tampilan laporan.
// OCP: memanggil r.keterangan() tanpa mengecek jenis rekening.
public class LaporanBank {
    private boolean sudahCetak = false;

    // pembantu untuk judul seksi (supaya main tidak perlu printf)
    public void cetakJudul(String judul) {
        if (sudahCetak) {
            System.out.println();
        }
        sudahCetak = true;
        StringBuilder sb = new StringBuilder("========== " + judul + " ");
        while (sb.length() < 35) {
            sb.append('=');
        }
        System.out.println(sb);
    }

    public void cetakDaftar(String judul, Rekening[] semua) {
        cetakJudul(judul);
        for (Rekening r : semua) {
            System.out.println(String.format("%s | %-6s | %12s",
                    r.getNomor(), r.getPemilik(), Rekening.formatRupiah(r.getSaldo())));
            System.out.println("      " + r.keterangan());
        }
    }

    public void cetakTotal(Rekening[] semua) {
        int total = 0;
        for (Rekening r : semua) {
            total += r.getSaldo();
        }
        System.out.println("-----------------------------------");
        System.out.println("Total dana bank : " + Rekening.formatRupiah(total));
    }
}