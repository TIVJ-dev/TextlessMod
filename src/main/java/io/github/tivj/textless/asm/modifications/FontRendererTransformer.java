package io.github.tivj.textless.asm.modifications;

import io.github.tivj.textless.asm.tweaker.transformer.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class FontRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.gui.FontRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            String methodName = mapMethodName(classNode, methodNode);
            if (methodName.equals("renderString")) {
                ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                while (iterator.hasNext()) {
                    AbstractInsnNode node = iterator.next();
                    if (node.getOpcode() == Opcodes.GETFIELD) {
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) node;
                        if (
                                (fieldInsnNode.owner.equals("net/minecraft/client/gui/FontRenderer") || fieldInsnNode.name.equals("srg")) &&
                                (fieldInsnNode.name.equals("bidiFlag") || fieldInsnNode.name.equals("srg")) &&
                                fieldInsnNode.desc.equals("Z")
                        ) {
                            methodNode.instructions.insertBefore(node.getPrevious(), textless());
                            return;
                        }
                    }
                }
            }
        }
    }

    private InsnList textless() {
        InsnList list = new InsnList();
        LabelNode labelNode = new LabelNode();
        list.add(new VarInsnNode(Opcodes.ALOAD,1));
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "io/github/tivj/textless/ToggleCommand","textEnabled","Z"));
        list.add(new JumpInsnNode(Opcodes.IFEQ, labelNode));

        list.add(new InsnNode(Opcodes.ICONST_0));
        list.add(new InsnNode(Opcodes.IRETURN));

        list.add(labelNode);
        return list;
    }
}
