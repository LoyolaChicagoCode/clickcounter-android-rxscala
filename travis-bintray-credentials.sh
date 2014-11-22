#!/bin/sh

FILE=$HOME/.curlrc
cat <<EOF > $FILE
user=${BINTRAY_USER}:${BINTRAY_API_KEY}
EOF

echo "Created $FILE containing bintray credentials:"
ls -la $FILE
