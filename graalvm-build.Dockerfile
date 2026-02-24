# --- Stage 1: Build native-image ---
FROM graalvm-ee-ready AS builder

WORKDIR /app
COPY . /app

RUN ./mvnw clean native:compile -Pnative -DskipTests


# --- Stage 2: Runtime (có thể dùng hoặc chỉ để extract) ---
FROM busybox:glibc AS runtime

WORKDIR /dist
COPY --from=builder /app/target/rpg_2d_router_server .

# Không cần ENTRYPOINT nếu chỉ dùng để extract
