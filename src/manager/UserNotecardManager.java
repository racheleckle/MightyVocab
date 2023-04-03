package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model_classes.User;
import model_classes.UserSets;

public class UserNotecardManager {

	private final List<UserSets> allRequests = new ArrayList<>();
	private final List<UserSets> confirmedRequests = new ArrayList<>();
	private final List<UserSets> awaitingRequests = new ArrayList<>();
	private int numberOfRequests;
	private User user;

	private static final UserNotecardManager INSTANCE = new UserNotecardManager();

	private UserNotecardManager() {
	}

	public static UserNotecardManager getInstance() {
		return INSTANCE;
	}

	public boolean addUserRequest(UserSets userRequest) {
		Objects.requireNonNull(userRequest, "Request cannot be null");
		boolean added = false;
		if (userRequest.getStatus().equals("AWAITING")) {
			added = awaitingRequests.add(userRequest);
		} else {
			added = confirmedRequests.add(userRequest);
		}
		if (added) {
			allRequests.add(userRequest);
			numberOfRequests++;
		}
		return added;
	}

	public void updateRequestsLists(UserSets request) {
		Objects.requireNonNull(request, "Request cannot be null");
		if (awaitingRequests.remove(request)) {
			confirmedRequests.add(request);
		}
	}

	public List<UserSets> getConfirmedRequests() {
		return Collections.unmodifiableList(confirmedRequests);
	}

	public List<UserSets> getAwaitingRequests() {
		return Collections.unmodifiableList(awaitingRequests);
	}

	public int getNumberOfRequests() {
		return numberOfRequests;
	}

	public List<UserSets> getAllRequests() {
		return Collections.unmodifiableList(allRequests);
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
