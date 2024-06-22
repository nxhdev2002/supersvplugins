package hoangdz.discord.dokiep.Dto;

public class PlayerUpLevelMsgDto {
    public String Player;
    public int OldLevel;
    public int NewLevel;

    public PlayerUpLevelMsgDto(String player, int oldLevel, int newLevel) {
        Player = player;
        OldLevel = oldLevel;
        NewLevel = newLevel;
    }
}
