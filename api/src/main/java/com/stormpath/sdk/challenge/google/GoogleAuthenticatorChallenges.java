package com.stormpath.sdk.challenge.google;

import com.stormpath.sdk.challenge.*;
import com.stormpath.sdk.lang.Classes;
import com.stormpath.sdk.query.Criterion;
import com.stormpath.sdk.query.EqualsExpressionFactory;
import com.stormpath.sdk.query.StringExpressionFactory;

import java.lang.reflect.Constructor;

/**
 * todo: mehrshad
 */
public class GoogleAuthenticatorChallenges {
    private static final GoogleAuthenticatorChallenges INSTANCE;

    static{
        INSTANCE = new GoogleAuthenticatorChallenges();
    }

    private static final Class<CreateChallengeRequestBuilder> BUILDER_CLASS =
            Classes.forName("com.stormpath.sdk.impl.challenge.DefaultCreateChallengeRequestBuilder");

    //prevent instantiation outside of outer class.
    // Use getInstance() to retrieve the singleton instance.
    private GoogleAuthenticatorChallenges() {
    }

    public static final GoogleAuthenticatorChallenges getInstance(){
        return INSTANCE;
    }

    /**
     * Returns a new {@link ChallengeCriteria} instance to use to formulate a Challenge query.
     * <p/>
     * Note that it is usually more common to use the {@link #where(Criterion) where} method
     * instead of this one as the {@code where} method usually lends to better readability.  For example:
     * <pre>
     * Challenges.criteria().add(Challenges.status().eq(ChallengeStatus.CREATED))...
     * </pre>
     * versus:
     * <pre>
     * Challenges.where(Challenges.status().eq(ChallengeStatus.CREATED))...
     * </pre>
     * or when using static imports:
     * <pre>
     * where(status().eq(ChallengeStatus.CREATED))
     * </pre>
     * While all three statements are equivalent, the second and third examples are shorter and probably more readable.
     *
     * @return a new {@link ChallengeCriteria} instance to use to formulate a Challenge query.
     */
    public static GoogleAuthenticatorChallengeCriteria criteria() {
        return (GoogleAuthenticatorChallengeCriteria) Classes.newInstance("com.stormpath.sdk.impl.challenge.google.DefaultGoogleAuthenticatorChallengeCriteria");
    }

    public static ChallengeOptions<ChallengeOptions> options() {
        return (ChallengeOptions) Classes.newInstance("com.stormpath.sdk.impl.challenge.DefaultChallengeOptions");
    }

    /**
     * Creates a new {@link GoogleAuthenticatorChallengeCriteria} instance using the specified {@code criterion} as the first query condition.
     *
     * @return a new {@link GoogleAuthenticatorChallengeCriteria} instance using the specified {@code criterion} as the first query condition.
     */
    public static GoogleAuthenticatorChallengeCriteria where(Criterion criterion) {return (GoogleAuthenticatorChallengeCriteria)criteria().add(criterion);}

    /**
     * Creates a new {@link EqualsExpressionFactory} instance reflecting the Challenge {@link Challenge#getStatus() status}
     * property, to be used to construct a status Criterion when building an {@link ChallengeCriteria} query.  For example:
     * <pre>
     * Challenges.where(<b>Challenges.status()</b>.eq(ChallengeStatus.CREATED);
     * </pre>
     * The above example invokes the returned factory's <code>eq()</code> method.  This
     * produces a status-specific {@link Criterion} which is added to the criteria query (via the
     * {@link #where(Criterion) where} method).  For example, the following code is equivalent:
     * <pre>
     * ChallengeCriteria criteria = Challenges.criteria();
     * StringExpressionFactory statusExpressionFactory = Challenges.status();
     * Criterion statusEqualsEnabled = statusExpressionFactory.eq(ChallengeStatus.CREATED);
     * criteria.add(statusEqualsEnabled);
     * </pre>
     * The first code example is clearly more succinct and readable.
     *
     * @return a new {@link Challenge#getStatus() status}-specific {@link StringExpressionFactory} instance, to be
     *         used to construct a criterion when building an {@link ChallengeCriteria} query.
     */
    public static EqualsExpressionFactory status() {
        return newEqualsExpressionFactory("status");
    }

    /**
     * Creates a new {@link CreateChallengeRequestBuilder CreateChallengeRequestBuilder}
     * instance reflecting the specified {@link Challenge} instance. The builder can be used to customize any
     * creation request options as necessary.
     *
     * @param challenge the challenge to create a new record for within Stormpath
     * @return a new {@link CreateChallengeRequestBuilder CreateChallengeRequestBuilder}
     *         instance reflecting the specified {@link Challenge} instance.
     * @see com.stormpath.sdk.factor.google.GoogleAuthenticatorFactor#createChallenge(CreateChallengeRequest)
     *
     * @since 1.1.0
     */
    public static CreateChallengeRequestBuilder<GoogleAuthenticatorChallenge> newCreateRequestFor(GoogleAuthenticatorChallenge challenge) {
        Constructor ctor = Classes.getConstructor(BUILDER_CLASS, Challenge.class);
        return (CreateChallengeRequestBuilder<GoogleAuthenticatorChallenge>) Classes.instantiate(ctor, challenge);
    }

    private static EqualsExpressionFactory newEqualsExpressionFactory(String propName) {
        final String FQCN = "com.stormpath.sdk.impl.query.DefaultEqualsExpressionFactory";
        return (EqualsExpressionFactory) Classes.newInstance(FQCN, propName);
    }

    private static StringExpressionFactory newStringExpressionFactory(String propName) {
        final String FQCN = "com.stormpath.sdk.impl.query.DefaultStringExpressionFactory";
        return (StringExpressionFactory) Classes.newInstance(FQCN, propName);
    }
}
