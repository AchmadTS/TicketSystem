package controller;

public class HapusPenerbangan {
    private SistemTiket sistem;

    public HapusPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.print("Masukkan ID penerbangan: ");
        int id = Integer.parseInt(sistem.input.nextLine());
        int idx = -1;

        for (int i = 0; i < sistem.jumlahPenerbangan; i++) {
            if (sistem.daftarPenerbangan[i].id == id) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }

        for (int i = idx; i < sistem.jumlahPenerbangan - 1; i++)
            sistem.daftarPenerbangan[i] = sistem.daftarPenerbangan[i + 1];

        sistem.jumlahPenerbangan--;
        System.out.println("🗑️  Dihapus.");
    }
}
