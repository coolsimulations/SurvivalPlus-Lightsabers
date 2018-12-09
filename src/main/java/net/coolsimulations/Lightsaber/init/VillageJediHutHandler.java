package net.coolsimulations.Lightsaber.init;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class VillageJediHutHandler implements VillagerRegistry.IVillageCreationHandler{

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i){
        return new StructureVillagePieces.PieceWeight(StructureVillageJediHut.class, 5, MathHelper.getRandomIntegerInRange(random, 0, 1 + i));
    }

    @Override
    public Class<?> getComponentClass(){
        return StructureVillageJediHut.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        return StructureVillageJediHut.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }
}
