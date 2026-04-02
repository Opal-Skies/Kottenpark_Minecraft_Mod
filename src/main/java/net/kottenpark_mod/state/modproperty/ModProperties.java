package net.kottenpark_mod.state.modproperty;

import net.kottenpark_mod.block.enums.QandA;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;

public class ModProperties extends Properties {
    public static final BooleanProperty SUCCEEDED = BooleanProperty.of("succeeded");
    public static final EnumProperty<QandA> Q_AND_A = EnumProperty.of("qanda", QandA.class);
}
