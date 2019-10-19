import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should return a list of cars")
    request {
        url "/cars"
        method GET()
    }
    response {
        status 200
        body("[{\"brandName\":\"fiat\",\"model\":\"125p\"},{\"brandName\":\"bmw\",\"model\":\"3\"}]")
    }
}