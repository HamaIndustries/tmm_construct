package hama.industries.tmm.construct;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TmmConstructDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
//        fabricDataGenerator.createPack().addProvider(TCItemModels::new);
    }

//    private static class TCItemModels extends FabricModelProvider {
//        public TCItemModels(FabricDataOutput output) {
//            super(output);
//        }
//
//        @Override
//        public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
//
//        }
//
//        @Override
//        public void generateItemModels(ItemModelGenerators itemModelGenerators) {
//            itemModelGenerators.generateFlatItem(TmmConstruct.THETISCOPE, ModelTemplates.FLAT_ITEM);
//        }
//    }
}
