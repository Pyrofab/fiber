package me.zeroeightsix.fiber.tree;

import me.zeroeightsix.fiber.exception.DuplicateChildException;

import javax.annotation.Nullable;
import java.util.Collection;

public interface NodeCollection extends Collection<ConfigNode> {
    /**
     * Attempts to introduce a new child to this collection.
     *
     * <p> This method behaves as if {@code add(node, false)}.
     *
     * @param child The child to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws DuplicateChildException if there was already a child by the same name
     * @throws NullPointerException if {@code node} is null
     */
    @Override
    boolean add(ConfigNode child) throws DuplicateChildException;

    /**
     * Attempts to introduce a new child to this collection.
     *
     * @param child      The child to add
     * @param overwrite whether existing items with the same name should be overwritten
     * @throws DuplicateChildException if there exists a child by the same name that was not overwritten
     * @throws NullPointerException if {@code node} is null
     */
    boolean add(ConfigNode child, boolean overwrite) throws DuplicateChildException;

    ConfigNode getByName(String name);

    @Nullable
    ConfigNode removeByName(String name);
}
