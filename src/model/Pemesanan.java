package model;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Pemesanan {
    public int idPemesanan;
    public int idPenerbangan;
    public String namaPelanggan;
    public int jumlah;
    public double totalHarga;
    public LocalDateTime waktuPesan;

    public Pemesanan(int idPemesanan, int idPenerbangan, String namaPelanggan, int jumlah, double totalHarga, LocalDateTime waktuPesan) {
        this.idPemesanan = idPemesanan;
        this.idPenerbangan = idPenerbangan;
        this.namaPelanggan = namaPelanggan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.waktuPesan = waktuPesan;
    }

    public String getNamaBulan(int bulan) {
        String nama = "";
        switch (bulan) {
            case 1:
                nama = "Januari";
                break;
            case 2:
                nama = "Februari";
                break;
            case 3:
                nama = "Maret";
                break;
            case 4:
                nama = "April";
                break;
            case 5:
                nama = "Mei";
                break;
            case 6:
                nama = "Juni";
                break;
            case 7:
                nama = "Juli";
                break;
            case 8:
                nama = "Agustus";
                break;
            case 9:
                nama = "September";
                break;
            case 10:
                nama = "Oktober";
                break;
            case 11:
                nama = "November";
                break;
            case 12:
                nama = "Desember";
                break;
            default:
                nama = "Bulan tidak valid";
                break;
        }
        return nama;
    }

    public String ringkasan(Penerbangan penerbangan) {
        String infoPenerbangan = "";

        if (penerbangan != null) {
            String namaBulan = getNamaBulan(penerbangan.bulan);
            infoPenerbangan = penerbangan.pesawat + " "
                    + penerbangan.asal + " -> "
                    + penerbangan.tujuan + " ("
                    + penerbangan.hari + " "
                    + namaBulan + " "
                    + penerbangan.tahun + ")";
        } else {
            infoPenerbangan = "Penerbangan sudah dihapus";
        }

        String waktu = waktuPesan.getYear() + "-"
                + waktuPesan.getMonthValue() + "-"
                + waktuPesan.getDayOfMonth() + " "
                + waktuPesan.getHour() + ":"
                + waktuPesan.getMinute();

        DecimalFormat df = new DecimalFormat("#,###");
        return "ID Pesanan #" + idPemesanan
                + " | Nama: " + namaPelanggan
                + " | " + infoPenerbangan
                + " | Jumlah: " + jumlah
                + " | Total: Rp" + df.format(totalHarga)
                + " | Waktu Pesan: " + waktu;
    }
}
