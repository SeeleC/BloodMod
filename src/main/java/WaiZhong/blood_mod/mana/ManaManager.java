package WaiZhong.blood_mod.mana;

import net.minecraft.nbt.NbtCompound;

public class ManaManager {

    private boolean hasMana = true;
    private int manaLevel = 20;

    public void add(int mana) {
        this.manaLevel = Math.min(mana + this.manaLevel, 20);
    }

    public void readNbt(NbtCompound tag) {
        if (tag.contains("manaLevel", 99)) {
            this.manaLevel = tag.getInt("ManaLevel");
            this.hasMana = tag.getBoolean("HasMana");
        }
    }

    public void writeNbt(NbtCompound tag) {
        tag.putInt("ManaLevel", this.manaLevel);
        tag.putBoolean("HasMana", this.hasMana);
    }

    public int getManaLevel() {
        return this.manaLevel;
    }

    public boolean isNotFull() {
        return this.manaLevel < 20;
    }

    public void setManaLevel(int thirstLevel) {
        this.manaLevel = thirstLevel;
    }

    public boolean hasMana() {
        return this.hasMana;
    }

    public void setMana(boolean canHaveMana) {
        this.hasMana = canHaveMana;
    }
}
