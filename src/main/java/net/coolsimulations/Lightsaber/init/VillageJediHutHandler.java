package net.coolsimulations.Lightsaber.init;

/**import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.structure.VillagePieces.PieceWeight;
import net.minecraftforge.fml.common.registry.VillagerRegistry;**/

public class VillageJediHutHandler /*implements VillagerRegistry.IVillageCreationHandler*/{

    /**@Override
    public VillagePieces.PieceWeight getVillagePieceWeight(Random random, int i){
        return new VillagePieces.PieceWeight(StructureVillageJediHut.class, 5, MathHelper.nextInt(random, 0, 1 + i));
    }

    @Override
    public Class<?> getComponentClass(){
        return StructureVillageJediHut.class;
    }

    @Override
    public VillagePieces.Village buildComponent(PieceWeight villagePiece, VillagePieces.Start startPiece, List<StructurePiece> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        return StructureVillageJediHut.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }**/

}
