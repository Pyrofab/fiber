package me.zeroeightsix.fiber;

import blue.endless.jankson.JsonArray;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import me.zeroeightsix.fiber.builder.constraint.CompositeConstraintBuilder;
import me.zeroeightsix.fiber.constraint.Constraint;
import me.zeroeightsix.fiber.constraint.ValuedConstraint;
import me.zeroeightsix.fiber.ir.ConfigValue;
import me.zeroeightsix.fiber.ir.ConfigNode;

import java.util.List;

public class Schemas {

	public static JsonObject createSchema(ConfigNode settings) {
		JsonObject object = new JsonObject();

		settings.getSettingsImmutable().forEach((key, setting) -> object.put((String) key, createSchema((ConfigValue) setting)));
		settings.getSubSettingsImmutable().forEach((key, settingsObject) -> object.put((String) key, createSchema((ConfigNode) settingsObject)));

		return object;
	}

	private static <T> JsonObject createSchema(ConfigValue<T> configValue) {
		JsonObject object = new JsonObject();
		object.put("comment", new JsonPrimitive(configValue.getComment()));
		object.put("class", new JsonPrimitive(configValue.getType().getTypeName()));
		object.put("constraints", createSchema(configValue.getConstraintList()));
		return object;
	}

	private static <T> JsonElement createSchema(List<Constraint<T>> constraintList) {
		JsonArray array = new JsonArray();
		for (Constraint<T> constraint : constraintList) {
			JsonObject object = new JsonObject();
			object.put("identifier", new JsonPrimitive(constraint.getType().getIdentifier().toString()));
			if (constraint instanceof ValuedConstraint) {
				object.put("value", new JsonPrimitive(((ValuedConstraint) constraint).getValue()));
			}
			if (constraint instanceof CompositeConstraintBuilder.AbstractCompositeConstraint) {
				object.put("constraints", createSchema(((CompositeConstraintBuilder.AbstractCompositeConstraint) constraint).constraints));
			}
			array.add(object);
		}
		return array;
	}

}
