package de.MagicThinks.halloweenevent.client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;

public class ClientEvents implements ClientTickEvents.EndTick {

    ArrayList<Integer> Itemlist = new ArrayList<Integer>();

    @Override
    public void onEndTick(MinecraftClient client) {
        ClientWorld world = client.world;
        Item wantedItem = Items.FEATHER;
            try {
                assert world != null;
                Iterable<Entity> s = world.getEntities();
                s.forEach(entity -> {
                    if(entity.getType().equals(EntityType.ITEM)){
                        ItemEntity item = (ItemEntity) entity;
                        ItemStack stack = item.getStack();

                        if(stack.getItem().equals(wantedItem)){
                            if(!Itemlist.contains(item.getId())){
                                Itemlist.add(item.getId());
                                int x = (int) item.getX();
                                int y = (int) item.getY();
                                int z = (int) item.getZ();
                                //System.out.println("X: " + x + " Y: " + y + " Z: "+ z);
                                addMSG("X: " + x + " Y: " + y + " Z: "+ z, client);
                                write(x,y,z);
                            }
                            world.addImportantParticle(ParticleTypes.EXPLOSION, true, item.getX(), item.getY() + 2.0, item.getZ(), 0.0, 0.0, 0.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 1D, item.getZ(), 5.0, 5.0, 5.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 2D, item.getZ(), 0.0, 0.0, 0.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 3D, item.getZ(), 0.0, 0.0, 0.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 4D, item.getZ(), 0.0, 0.0, 0.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 5D, item.getZ(), 0.0, 0.0, 0.0);
                            world.addImportantParticle(ParticleTypes.ELECTRIC_SPARK, true, item.getX(), item.getY() + 6D, item.getZ(), 0.0, 0.0, 0.0);

                        }
                    }
                });
            }catch (Exception ex) {
                // TODO: handle exception
            }
    }

    private void addMSG(String msg, MinecraftClient mc) {
        Text text = Text.empty().append(msg);
        mc.inGameHud.getChatHud().addMessage(text);

    }

    /**
     * Save Coords to File. Here for Windows
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     */
    private void write(int x, int y, int z) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("C:\\\\temp\\\\coords2.txt", true), true);
            pw.println(x + " | " + y + " | " + z);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}