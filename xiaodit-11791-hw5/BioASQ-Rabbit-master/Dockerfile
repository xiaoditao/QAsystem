FROM python:2.7-slim
FROM docker.lappsgrid.org/deiis/base

COPY *.py /root/
WORKDIR /root

ENTRYPOINT ["python", “pipeline.py"]