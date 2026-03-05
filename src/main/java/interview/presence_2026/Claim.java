package interview.presence_2026;

import java.util.ArrayList;
import java.util.List;

/**
 * ## 题目一：游戏邮箱拆包
 * > 示例email 1：
 * > - 10 金币
 * > - 5  皮肤
 * > - 1  宝箱（可能包含金币、皮肤、宝箱）
 *
 * > 示例email 2：
 * > - xx 金币
 * > - xx 宝箱（可能包含金币、皮肤、宝箱）
 *
 * > 示例email 3：
 * > - xx 皮肤
 * > - xx 宝箱（可能包含金币、皮肤、宝箱）
 *
 * > 示例email 4：
 * > - xx 皮肤
 * > - xx 金币
 *
 * 要求设计一个一键拆包功能，将一组邮箱拆开到背包，宝箱可继续拆包，背包有容量限制，例如皮肤最大容量为10，金币无上限。
 * 若拆包后背包容量充足则邮箱更新为已读状态，若容量不足则邮件停留在未读状态。
 */

public class Claim {

    enum ItemType {
        COIN,
        SKIN,
        CHEST
    }

    class GlobalConfig {
        public static final int SKIN_MAX = 10; // 皮肤上限
    }

    // 全局背包
    private static class Backpack {
        public static Backpack instance = new Backpack();

        private int coin;
        private int skin;

        private Backpack() {
            this.coin = 0;
            this.skin = 0;
        }

        // 只需要：判断皮肤容量是否足够
        public boolean canAddSkin(int addSkin) {
            return this.skin + addSkin <= GlobalConfig.SKIN_MAX;
        }

        // 添加物品
        public void addCoin(int count) { this.coin += count; }
        public void addSkin(int count) { this.skin += count; }

        // getter
        public int getCoin() { return coin; }
        public int getSkin() { return skin; }
    }

    // 物品定义（面试核心）
    private static class Item {
        private ItemType type;
        private int count;
        private List<Item> chestContent; // 宝箱内容

        // 金币/皮肤
        public Item(ItemType type, int count) {
            this.type = type;
            this.count = count;
            this.chestContent = new ArrayList<>();
        }

        // 宝箱
        public Item(int count, List<Item> chestContent) {
            this.type = ItemType.CHEST;
            this.count = count;
            this.chestContent = chestContent;
        }

        // getter
        public ItemType getType() { return type; }
        public int getCount() { return count; }
        public List<Item> getChestContent() { return chestContent; }
    }

    // 邮件
    private static class Email {
        private int id;
        private List<Item> attachment;
        private boolean isClaim;

        // getter & setter
        public List<Item> getAttachment() { return attachment; }
        public void setAttachment(List<Item> attachment) { this.attachment = attachment; }
        public boolean isClaim() { return isClaim; }
        public void setClaim(boolean claim) { isClaim = claim; }
    }

    // 核心一键拆包
    public static void claim(List<Email> emailList) {
        if (emailList == null) return;

        for (Email email : emailList) {
            if (email.isClaim() || email.getAttachment() == null) {
                continue;
            }

            // 🔥 只计算：拆包后会增加多少皮肤（金币完全不计算）
            int addSkin = calculateTotalSkin(email.getAttachment());

            // 判断容量
            if (Backpack.instance.canAddSkin(addSkin)) {
                // 足够：真正拆包 + 标记已读
                doUnpack(email.getAttachment());
                email.setClaim(true);
            }
            // 不足：什么都不做，保持未读
        }
    }

    /**
     * 🔥 只递归计算：总新增皮肤数量（金币直接忽略）
     */
    private static int calculateTotalSkin(List<Item> items) {
        int addSkin = 0;
        for (Item item : items) {
            switch (item.getType()) {
                case SKIN:
                    addSkin += item.getCount();
                    break;
                case CHEST:
                    // 宝箱：递归累加皮肤
                    for (int i = 0; i < item.getCount(); i++) {
                        addSkin += calculateTotalSkin(item.getChestContent());
                    }
                    break;
                case COIN:
                    // ✅ 金币：直接忽略，不计算
                    break;
            }
        }
        return addSkin;
    }

    /**
     * 真正执行拆包（加金币、加皮肤）
     */
    private static void doUnpack(List<Item> items) {
        for (Item item : items) {
            switch (item.getType()) {
                case COIN:
                    Backpack.instance.addCoin(item.getCount());
                    break;
                case SKIN:
                    Backpack.instance.addSkin(item.getCount());
                    break;
                case CHEST:
                    for (int i = 0; i < item.getCount(); i++) {
                        doUnpack(item.getChestContent());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // 1. 初始化系统
        Backpack backpack = Backpack.instance;

        // 2. 构造宝箱内容（1个宝箱=2金币+1皮肤）
        List<Item> chestContent = new ArrayList<>();
        chestContent.add(new Item(ItemType.COIN, 2));
        chestContent.add(new Item(ItemType.SKIN, 1));

        // 3. 构造邮件1：10金币 + 5皮肤 + 1宝箱
        List<Item> attach1 = new ArrayList<>();
        attach1.add(new Item(ItemType.COIN, 10));
        attach1.add(new Item(ItemType.SKIN, 5));
        attach1.add(new Item(1, chestContent)); // 1个宝箱

        Email email1 = new Email();
        email1.setAttachment(attach1);

        // 4. 批量处理邮件
        List<Email> emailList = new ArrayList<>();
        emailList.add(email1);
        claim(emailList);

        // 输出结果
        System.out.println("邮件是否已读：" + email1.isClaim()); // true
        System.out.println("背包金币：" + backpack.getCoin()); // 12
        System.out.println("背包皮肤：" + backpack.getSkin()); // 6
    }

}
