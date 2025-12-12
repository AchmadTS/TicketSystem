package view;

import model.Pemesanan;
import model.Penerbangan;
import java.text.DecimalFormat;

public class EditPesananView {
    private DecimalFormat df = new DecimalFormat("#,###");
    public void showHeader() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║              ✏️  EDIT PEMESANAN TIKET                   ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
    }

    /**
     * Pesan jika belum ada pemesanan
     */
    public void showTidakAdaPemesanan() {
        System.out.println("\n⚠️ Belum ada pemesanan yang bisa diedit");
    }

    /**
     * Data pemesanan sebelum di-edit
     */
    public void showDataPemesananSaatIni(Pemesanan pemesanan, Penerbangan penerbangan) {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                 📋 DATA PEMESANAN SAAT INI               │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println("ID Pemesanan    : " + pemesanan.idPemesanan);
        System.out.println("Nama Pemesan    : " + pemesanan.namaPelanggan);
        System.out.println("Jumlah Tiket    : " + pemesanan.jumlah);
        System.out.println("Harga/Tiket     : Rp" + df.format(penerbangan.harga));
        System.out.println("Total Harga     : Rp" + df.format(pemesanan.totalHarga));
        System.out.println("ID Penerbangan  : " + penerbangan.id);
        System.out.println("Penerbangan     : " + penerbangan.pesawat + " (" + penerbangan.asal + " → " + penerbangan.tujuan + ")");
        System.out.println();
        System.out.println("--- Edit Data ---");
        System.out.println();
    }

    public void showHeaderDaftarPenerbangan() {
        System.out.println();
        System.out.println("📋 Daftar Penerbangan Tersedia:");
    }

    /**
     * Pesan error ketika kursi tidak cukup
     */
    public void showKursiTidakCukup(int kursiTersedia) {
        System.out.println("❌ Kursi tidak cukup di penerbangan baru! Tersedia: " + kursiTersedia + " kursi");
        System.out.println("⚠️ Penerbangan tidak diubah.");
    }

    /**
     * Konfirmasi kursi dikembalikan ke penerbangan lama
     */
    public void showKursiDikembalikan(int jumlahKursi) {
        System.out.println("✅ Kursi dikembalikan ke penerbangan lama: +" + jumlahKursi + " kursi");
    }

    /**
     * Konfirmasi kursi dipesan dari penerbangan baru
     */
    public void showKursiDipesan(int jumlahKursi) {
        System.out.println("✅ Kursi dipesan dari penerbangan baru: -" + jumlahKursi + " kursi");
    }

    /**
     * Konfirmasi penerbangan berhasil diganti
     */
    public void showPenerbanganBerhasilDiganti() {
        System.out.println("✅ Penerbangan berhasil diganti!");
    }

    /**
     * Kursi tidak cukup saat edit jumlah
     */
    public void showKursiTidakCukupEdit(int kursiTersedia) {
        System.out.println("❌ Kursi tidak cukup! Tersedia: " + kursiTersedia + " kursi");
        System.out.println("   Silakan masukkan jumlah yang lebih sedikit.");
    }

    /**
     * Konfirmasi jumlah tiket berhasil diperbarui (berkurang)
     */
    public void showJumlahTiketBerkurang(int selisih) {
        System.out.println("✅ Jumlah tiket berhasil diperbarui!");
        System.out.println("   Kursi penerbangan berkurang: " + selisih + " kursi");
    }

    /**
     * Konfirmasi jumlah tiket berhasil diperbarui (bertambah)
     */
    public void showJumlahTiketBertambah(int selisih) {
        System.out.println("✅ Jumlah tiket berhasil diperbarui!");
        System.out.println("   Kursi penerbangan bertambah: " + selisih + " kursi");
    }

    /**
     * Pesan jumlah tiket tidak berubah
     */
    public void showJumlahTidakBerubah() {
        System.out.println("ℹ️  Jumlah tiket tidak berubah.");
    }

    /**
     * Konfirmasi pemesanan berhasil diperbarui dan data baru
     */
    public void showKonfirmasiUpdate(Pemesanan pemesanan, Penerbangan penerbangan) {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║          ✅ PEMESANAN BERHASIL DIPERBARUI!              ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                   📋 DATA PEMESANAN BARU                 │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println("ID Pemesanan  : " + pemesanan.idPemesanan);
        System.out.println("Nama Pemesan  : " + pemesanan.namaPelanggan);
        System.out.println("Jumlah Tiket  : " + pemesanan.jumlah);
        System.out.println("Harga/Tiket   : Rp" + df.format(penerbangan.harga));
        System.out.println("Total Harga   : Rp" + df.format(pemesanan.totalHarga));
        System.out.println("Penerbangan   : " + penerbangan.pesawat + " (" + penerbangan.asal + " → " + penerbangan.tujuan + ")");
        System.out.println("Kursi Tersedia: " + penerbangan.jumlahKursi);
        System.out.println("══════════════════════════════════════════════════════════");
    }
}