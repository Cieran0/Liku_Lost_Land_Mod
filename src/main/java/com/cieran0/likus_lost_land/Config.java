package com.cieran0.likus_lost_land;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = LikusLostLand.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

//    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);
//
//    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
//            .comment("A magic number")
//            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
//
//    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
//            .comment("What you want the introduction message to be for the magic number")
//            .define("magicNumberIntroduction", "The magic number is... ");
//
//    // a list of strings that are treated as resource locations for items
//    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    private static final List<String> Q_AND_A_LIST = List.of(
            "Who are you?", "I am Liku, a lost soul trapped in Minecraft.",
            "Where do you come from?", "I come from the End of the World.",
            "How can I help you?", "Carry me to my home at the End of the World.",
            "Where is your home?", "My home is at the End of the World.",
            "How do I find the End of the World?", "Travel in any direction until you reach it.",
            "Are there any dangers?", "The journey is long and perilous.",
            "What happens when you return?", "I will be at peace and the curse will lift.",
            "Why do you need my help?", "I can no longer move on my own.",
            "Will you reward me?", "You will have my eternal gratitude."
    );

    private static final ForgeConfigSpec.ConfigValue<List<String>> Q_AND_A = BUILDER
            .comment("Questions And Answers")
            .define("questions_and_answers", Q_AND_A_LIST);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    public static List<String> Questions = new ArrayList<String>();
    public static List<String> Answers = new ArrayList<String>();


    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        System.out.println("HELLO!!");

//        logDirtBlock = LOG_DIRT_BLOCK.get();
//        magicNumber = MAGIC_NUMBER.get();
//        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
//
//        // convert the list of strings into a set of items
//        items = ITEM_STRINGS.get().stream()
//                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
//                .collect(Collectors.toSet());

        List<String> qanda = Q_AND_A.get();

        for (int i = 0; i < qanda.size(); i++) {
            if(i % 2 == 0)
                Questions.add(qanda.get(i));
            else
                Answers.add(qanda.get(i));
        }

        for (int i = 0; i < Answers.size(); i++) {
            System.out.println(Questions.get(i) + " ?" + Answers.get(i));
        }
    }


}
