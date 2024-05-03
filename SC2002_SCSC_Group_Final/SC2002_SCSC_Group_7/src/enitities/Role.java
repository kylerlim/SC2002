package enitities;

/**
 * The `Role` enum represents the roles that entities can have in a camp.
 * Possible roles include UNINVOLVED, CREATOR, ATTENDEE, COMMITTEE, and BLACKLISTED.
 */
public enum Role {
	/** Represents an entity that is not involved in the camp. */
	UNINVOLVED,

	/** Represents the creator of the camp. */
	CREATOR,

	/** Represents an entity attending the camp. */
	ATTENDEE,

	/** Represents an entity assigned to the committee of the camp. */
	COMMITTEE,

	/** Represents an entity blacklisted from the camp. */
	BLACKLISTED
}