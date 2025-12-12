package controller.create;

import java.time.LocalDateTime;
import controller.SistemTiket;
import model.Penerbangan;
import model.Pemesanan;
import view.AddPesananView;
import util.Helper;

public class PesanTiket {
    private SistemTiket sistem;
    private AddPesananView view;
    public PesanTiket(SistemTiket sistem) {
        this.sistem = sistem;
        this.view = new AddPesananView();
    }

    public void run() {
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
        Penerbangan p = null;
        while (p == null) {
            int id = Helper.inputId(sistem.input, "Masukkan ID penerbangan: ");
            p = sistem.cariById(id);
            if (p == null) {
                System.out.println("❌ ID Tidak ada");
            }
        }

        String nama = Helper.inputStringWajib(sistem.input, "Nama pemesan: ");
        int jumlah = 0;
        boolean jumlahValid = false;
        while (!jumlahValid) {
            jumlah = Helper.inputInteger(sistem.input, "Jumlah tiket: ", 1, Integer.MAX_VALUE);
            if (jumlah > p.jumlahKursi) {
                System.out.println("❌ Kursi tidak cukup! Tersedia: " + p.jumlahKursi + " kursi");
            } else {
                jumlahValid = true;
            }
        }

        p.jumlahKursi -= jumlah;
        double total = jumlah * p.harga;
        sistem.daftarPemesanan[sistem.jumlahPemesanan++] = new Pemesanan(sistem.nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());
        view.showPemesanan(p, nama, jumlah, total);
    }
}