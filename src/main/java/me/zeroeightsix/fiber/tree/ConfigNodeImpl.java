package me.zeroeightsix.fiber.tree;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A commentable node.
 *
 * @see ConfigGroupImpl
 * @see ConfigLeaf
 */
public abstract class ConfigNodeImpl implements ConfigNode, Commentable {

    @Nonnull
    private final String name;
    @Nullable
    private final String comment;

    /**
     * Creates a new {@code ConfigLeaf}.
     *
     * @param name    the name for this leaf
     * @param comment the comment for this leaf
     */
    public ConfigNodeImpl(@Nonnull String name, @Nullable String comment) {
        this.name = name;
        this.comment = comment;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nullable
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[name=" + getName() + ", comment=" + getComment() + "]";
    }
}