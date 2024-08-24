# rate-limiter
Integrated RateLimiter


A Rate limiter is created using Interceptors, which allows us to intercept the request and count
the number of times the API has been called.

Below are the two apis in the project, one is rate limited and the other is not.
Rate limiting Configurations are present in the resources.

To test these APIs, install a plugin in VsCode called 'Rest Client' and in the file apis.http you will get options to  directly hit the APIs and get response.

GET http://localhost:8080/api/v1/rate-limited
X-USER-Id: user2

GET http://localhost:8080/api/v1/not-rate-limited
X-USER-Id: user2

Interceptors only intercept at the beginning, so if an API fails, the rate limiter will also include that case.


Another way of doing the same was, creating an annotation, and using Around aspect to pointcut the annotation.
This way we can check if an API is failing and count only the success APIs as part of rate limiter.

