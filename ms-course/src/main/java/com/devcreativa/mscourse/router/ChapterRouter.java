package com.devcreativa.mscourse.router;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.devcreativa.mscourse.handler.ChapterHandler;
import com.devcreativa.mscourse.model.entity.Chapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class ChapterRouter {
    String uri = "api/v1/chapter";

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/chapter",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = ChapterHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(
                            operationId = "findAll",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Chapter.class
                                            )))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/chapter/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = ChapterHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId = "findById",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Chapter.class
                                            ))),
                                    @ApiResponse(responseCode = "404", description = "Not found")
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            })
            ),
            @RouterOperation(
                    path = "/api/v1/chapter",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = ChapterHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Chapter.class
                                            ))
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Chapter.class
                                    ))
                            ))
            ),
            @RouterOperation(
                    path = "/api/v1/chapter/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.PUT,
                    beanClass = ChapterHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            operationId = "update",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = Chapter.class
                                            )))
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = Chapter.class
                                    ))
                            ),
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            }

                    )

            ),
            @RouterOperation(path = "/api/v1/chapter/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.DELETE,
                    beanClass = ChapterHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(operationId = "delete",
                            responses = {
                                    @ApiResponse(responseCode = "204", description = "No content"),
                                    @ApiResponse(responseCode = "404", description = "Not found")
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> chapterRoutes(ChapterHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET(uri), handler::findAll)
                .andRoute(RequestPredicates.GET(uri.concat("/{id}")), handler::findById)
                .andRoute(RequestPredicates.POST(uri), handler::save)
                .andRoute(RequestPredicates.PUT(uri.concat("/{id}")), handler::update)
                .andRoute(RequestPredicates.DELETE(uri.concat("/{id}")), handler::delete);

    }
}
