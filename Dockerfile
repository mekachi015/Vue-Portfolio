# Enhanced Root Dockerfile
FROM alpine:3.18

# Install necessary tools
RUN apk add --no-cache curl bash busybox-extras

# Create health check page
COPY health.html /health.html

# Create health check script
RUN echo '#!/bin/bash' > /health.sh && \
    echo 'if [ "$1" = "html" ]; then' >> /health.sh && \
    echo '  cat /health.html' >> /health.sh && \
    echo 'else' >> /health.sh && \
    echo '  echo "HTTP/1.1 200 OK"' >> /health.sh && \
    echo '  echo "Content-Type: text/plain"' >> /health.sh && \
    echo '  echo ""' >> /health.sh && \
    echo '  echo "✅ Portfolio Orchestrator is healthy"' >> /health.sh && \
    echo '  echo "📦 Services: Vue.js Frontend + Spring Boot Backend + MongoDB"' >> /health.sh && \
    echo 'fi' >> /health.sh && \
    chmod +x /health.sh

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1

# Start simple web server
CMD echo "🚀 Portfolio Application Orchestrator Starting..." && \
    echo "📊 Health endpoint: http://localhost:8080/health" && \
    echo "🌐 HTML endpoint: http://localhost:8080/" && \
    echo "" && \
    echo "This orchestrator container is running. For full functionality," && \
    echo "deploy with docker-compose to start all services." && \
    while true; do \
      { \
        echo -e "HTTP/1.1 200 OK\r"; \
        echo -e "Content-Type: text/html\r"; \
        echo -e "\r"; \
        cat /health.html; \
      } | nc -l -p 8080; \
    done